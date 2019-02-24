'use strict'

class State {
    constructor(props) {
        this.v = props.this
        this._dataCopy = null // TODO diff with state?
        this._log = [this._copy(this.v.state, true)]
    }

    _sort(event) {
        const column = event.target.cellIndex
        const descending = this.v.state.sortby === column && !this.v.state.descending
        const sign = descending ? -1 : 1
        const data = this._copy(this.v.state.data) // shallow copy
        data.sort((a, b) => {
            return a[column] > b[column] ? sign * 1 : sign * -1
        })
        this._logSetState({
            data: data,
            sortby: column,
            descending: descending,
        })
    }

    _changeToEditable(event) {
        this._logSetState({
            edit: {
                row: Number(event.target.dataset.row),
                cell: event.target.cellIndex
            }
        })
    }

    _save(event) {
        event.preventDefault()
        let input = event.target.firstChild
        let data = this._copy(this.v.state.data, true) // TODO should be shallow copy or deep copy?
        data[this.v.state.edit.row][this.v.state.edit.cell] = input.value
        this._logSetState({
            edit: null,
            data: data
        })
    }

    _search(event) {
        let needle = event.target.value.toLowerCase()
        if (!needle) {
            this._logSetState({data: this._dataCopy})
            return
        }
        let index = event.target.dataset.index
        let filteredData = this._dataCopy.filter(row => {
            return row[index].toString().toLowerCase().includes(needle)
        })
        this._logSetState({data: filteredData})
    }

    _toggleSearch(event) {
        if (this.v.state.search) {
            this._logSetState({
                data: this._dataCopy,
                search: !this.v.state.search,
                searchLabel: "Search"
            })
            this._dataCopy = null
        } else {
            this._dataCopy = this.v.state.data
            this._logSetState({
                search: !this.v.state.search,
                searchLabel: "Done"
            })
        }

    }

    _logSetState(nextState) {
        this._log.push(this._copy(nextState, true))
        this.v.setState(nextState) // TODO If @function{this.setState} will deep copy next state?
    }

    _replay() {
        console.log("there are ", this._log.length, " states", this._log)
        if (this._log.length <= 1) {
            console.log("No more state to replay yet")
            return
        }
        let i = 0
        // cleared interval will be completed still.
        const interval = setInterval(function () {
            this.v.setState(this._log[i++])
            if (i === this._log.length) {
                clearInterval(interval)
            }
        }.bind(this), 1000)
    }

    _copy(beCloned, deep) {
        if (deep) {
            return JSON.parse(JSON.stringify(beCloned))
        } else {
            if (Array.isArray(beCloned)) {
                return Array.from(beCloned)
            } else if(typeof beCloned === 'object' && beCloned !== null) { // TODO improve
                return Object.assign({}, beCloned)
            }
        }
        throw {
            code: "Copy Error",
            message: "Shallow copy for this type is not supported"
        }
    }

    _download(format, event) {
        var contents = format === 'json' ? JSON.stringify(this.v.state.data) : this.v.state.data.reduce(function (result, row) {
            return result
                + row.reduce(function (rowresult, cell, idx) {
                    return rowresult + '"' + cell.replace(/"/g, '""') + '"' + (idx < row.length - 1 ? ',' : '')
                }, '') + "\n"
        }, '')
        var blob = new Blob([contents], {type: 'text/' + format})

        var URL = window.URL || window.webkitURL
        event.target.href = URL.createObjectURL(blob)

        event.target.download = 'data.' + format
    }

}
