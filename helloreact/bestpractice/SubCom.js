'use strict'

class SubCom extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render() {
        return f.a({ //  TODO f is available!
            onClick: this._refresh.bind(this),
            href: ""
        }, "Refresh")
    }

    _refresh() {
        window.location.reload(true)
    }
}