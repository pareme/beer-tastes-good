import React, { Component } from 'react';

class AddBrewery extends Component {
  constructor(){
    super();
    this.state={
      newBrewery: {}
    }
  }

  handleSubmit(e){
    if(this.refs.name.value === '') {
      alert('name is required')
    } else {
      this.setState({ newBrewery: {
        name: this.refs.name.value,
        address: this.refs.address.value
      }
    }, function(){
      this.props.addBrewery(this.state.newBrewery);
    })
  }
  e.preventDefault();
}

render() {
  return (
    <div className="AddBrewery">
      <h3>Add Brewery</h3>
        <form onSubmit={this.handleSubmit.bind(this)}>
          <div>
            <label>Name</label>
            <input type="text" ref="name" />
          </div>
          <div>
            <label>Address</label>
            <input type="text" ref="address" />
          </div>
          <input type="submit" value="Submit" />
          </form>
    </div>
  );
}
}

AddBrewery.propTypes = {
  address: React.PropTypes.object,
}

export default AddBrewery;
