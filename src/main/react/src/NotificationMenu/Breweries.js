import React, { Component } from 'react';
import Brewery from '../Models/Brewery'

class Breweries extends Component {
  deleteBrewery(name){
    this.props.onDelete(name);
  }
  render() {
    let breweryMap;
    if(this.props.breweries) {
      breweryMap = this.props.breweries.map(brewery => {
        return (
          <Brewery onDelete={this.deleteBrewery.bind(this)} key={brewery.name} brewery={brewery} />
        )
      });
    }
    return (
      <div className="Breweries">
      <h3>Breweries</h3>
      {breweryMap}
      </div>
    );
  }
}

Breweries.propTypes = {
  breweries: React.PropTypes.array,
  onDelete: React.PropTypes.func
}

export default Breweries;
