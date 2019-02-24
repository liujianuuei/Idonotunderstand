'use strict'

const e = React.createElement
const f = ReactDOMFactories

class Excel extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            data: this.props.defaultData,
            sortby: null,
            descending: false,
            //edit: null, // not must have
            search: false,
            searchLabel: "Search",
            //dataCopy: null,
        }
        this._dataCopy = null // TODO diff with state?
        this._log = [this._copy(this.state, true)]
        //this._sort = this._sort.bind(this);
    }

    render() {
        return f.div(null, this._renderToolbar(), this._renderTable())
    }

    _renderTable() {
        return f.table(null, f.thead({
            onClick: event => {
                this._sort(event)
            }
        }, f.tr(null, this.props.headers.map((title, index) => {
            if (this.state.sortby === index) {
                title += this.state.descending ? "\u2191" : "\u2193"
            }
            return f.th({key: index}, title)
        }))), f.tbody({
            onDoubleClick: event => {
                this._showEditor(event)
            }
        }, this._renderSearch(), this.state.data.map((row, rowindex) => {
            return f.tr({key: rowindex}, row.map((cell, cellIndex) => {
                let content = cell
                let edit = this.state.edit
                if (edit && edit.row === rowindex && edit.cell === cellIndex) {
                    content = f.form({ // TODO Note: each cell is a form??
                        onSubmit: event => {
                            this._save(event)
                        }
                    }, f.input({
                        type: "text",
                        defaultValue: content
                    }))
                }
                return f.td({
                    key: cellIndex,
                    "data-row": rowindex // must have
                }, content)
            }))
        })))
    }

    _sort(event) {
        const column = event.target.cellIndex
        const descending = this.state.sortby === column && !this.state.descending
        const sign = descending ? -1 : 1
        const data = this._copy(this.state.data) // shallow copy
        data.sort((a, b) => {
            return a[column] > b[column] ? sign * 1 : sign * -1
        })
        this._logSetState({
            data: data,
            sortby: column,
            descending: descending,
        })
    }

    _showEditor(event) {
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
        let data = this._copy(this.state.data, true) // TODO should be shallow copy or deep copy?
        data[this.state.edit.row][this.state.edit.cell] = input.value
        this._logSetState({
            edit: null,
            data: data
        })
    }

    _renderSearch() {
        if (!this.state.search) {
            return null
        }
        return f.tr({
            onChange: event => {
                this._search(event)
            }
        }, this.props.headers.map((title, index) => {
            return f.td({key: index}, f.input({
                type: "text",
                "data-index": index
            }))
        }))
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

    _renderToolbar() {
        const search = f.button({
            onClick: event => {
                this._toggleSearch(event)
            }
        }, this.state.searchLabel)
        const exportJSON = f.a({
            onClick: this._download.bind(this, "json"),
            href: "data.json",
            download: "data.json"
        }, "Export JSON")
        const exportCSV = f.a({
            onClick: this._download.bind(this, "csv"),
            href: "data.csv",
            download: "data.csv"
        }, "Export CSV")
        return f.div({className: "toolbar"}, search, exportJSON, exportCSV)
    }

    _toggleSearch(event) {
        if (this.state.search) {
            this._logSetState({
                data: this._dataCopy,
                search: !this.state.search,
                searchLabel: "Search"
            })
            this._dataCopy = null
        } else {
            this._dataCopy = this.state.data
            this._logSetState({
                search: !this.state.search,
                searchLabel: "Done"
            })
        }

    }

    _logSetState(nextState) {
        this._log.push(this._copy(nextState, true))
        this.setState(nextState) // TODO If @function{this.setState} will deep copy next state?
    }

    componentDidMount() {
        document.onkeydown = function (event) { // TODO document ?!
            if (event.altKey && event.shiftKey && event.keyCode === 82) {
                this._replay()
            }
        }.bind(this)
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
            this.setState(this._log[i++])
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
            }
        }
        throw {
            code: "Copy Error",
            message: "Shallow copy for this type is not supported"
        }
    }

    _download(format, event) {
        var contents = format === 'json' ? JSON.stringify(this.state.data) : this.state.data.reduce(function (result, row) {
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
