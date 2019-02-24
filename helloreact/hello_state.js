"use strict"

const e = React.createElement

class HelloSubComponent extends React.Component {
    shouldComponentUpdate(nextProps, nextState) {
        console.log("HelloSubComponent::shouldComponentUpdate", "prevProps=" + JSON.stringify(nextProps), "prevState=" + JSON.stringify(nextState))
        return this.props.count !== nextProps.count
    }

    render() {
        return e('span', null, this.props.count)
    }
    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log("HelloSubComponent::componentDidUpdate::", "prevProps=" + JSON.stringify(prevProps), "prevState=" + JSON.stringify(prevState), "snapshot=" + snapshot)
    }


    // componentWillUnmount() {
    //     console.log("HelloSubComponent::", "componentWillUnmount")
    // }
}

class HelloState extends React.Component {
    //state = {text: "Hello State"}

    constructor(props) {
        super(props)
        this.state = {text: this.props.text || this.state} // must be state?
    }

    // shouldComponentUpdate(nextProps, nextState) {
    //     console.log("shouldComponentUpdate", "nextProps=" + JSON.stringify(nextProps), "nextState=" + JSON.stringify(nextState))
    //     return true
    // }

    render() {
        const ta = e("textarea", {
            key: "ta",
            defaultValue: this.state.text,
            onChange: (event) => {
                const com = event.target
                if (com.value.length <= 20) {
                    this.setState({text: com.value})
                } else {
                    com.value = com.value.substring(0, 20)
                }
            }
        })
        const h1 = e("h1", {key: "h1"}, e(HelloSubComponent, {count: this.state.text.length || null}))
        return e("div", null, ta, h1)
    }

    // getSnapshotBeforeUpdate(prevProps, prevState) {z
    //     console.log("getSnapshotBeforeUpdate", "prevProps=" + JSON.stringify(prevProps), "prevState=" + JSON.stringify(prevState))
    //     return "getSnapshotBeforeUpdate"
    // }
    //
    // componentDidUpdate(prevProps, prevState, snapshot) {
    //     console.log("componentDidUpdate::", "prevProps=" + JSON.stringify(prevProps), "prevState=" + JSON.stringify(prevState), "snapshot=" + snapshot)
    // }

    // componentWillUnmount() {
    //     console.log("componentWillUnmount")
    // }

}
