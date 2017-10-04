/**
 * Created by nicktodd on 19/05/2017.
 */
const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {compactDiscs: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/compactDiscs'}).done(response => {
            this.setState({compactDiscs: response.entity._embedded.employees});
    });
    }

    render() {
        return (
            <CompactDiscList compactDiscs={this.state.compactDiscs}/>
    )
    }
}


class CompactDiscList extends React.Component{
    render() {
        var compactDiscs = this.props.compactDiscs.map(compactDisc =>
            <CompactDisc key={compactDisc._links.self.href} compactDisc={compactDisc}/>
    );
        return (
            <table>
            <tbody>
            <tr>
            <th>Id</th>
        <th>Title</th>
        <th>Artist</th>
        </tr>
        {employees}
        </tbody>
        </table>
    )
    }
}

class CompactDisc extends React.Component{
    render() {
        return (
            <tr>
            <td>{this.props.compactDisc.id}</td>
        <td>{this.props.compactDisc.title}</td>
        <td>{this.props.compactDisc.artist}</td>
        </tr>
    )
    }
}

ReactDOM.render(
<App />,
    document.getElementById('react')
)