import React, { Component } from 'react';

class Brewery extends Component {
  deleteBrewery(name){
    this.props.onDelete(name);
  }
  render() {
    return (
      <li className="Brewery">
      <strong>{this.props.brewery.name}</strong> - {this.props.brewery.address} <a href="#" onClick={this.deleteBrewery.bind(this, this.props.brewery.name)}>X</a>
      </li>
    );
  }
}

Brewery.propTypes = {
  brewery: React.PropTypes.object,
  onDelete:React.PropTypes.func
}

export default Brewery;
