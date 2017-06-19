import React, { Component } from 'react';
import Breweries from './NotificationMenu/Breweries';
import NotificationMenu from './NotificationMenu/NavBar'
import './App.css';
import $ from 'jquery'

class App extends Component {
  constructor(){
    super()
    this.state = {
      breweries: [],
    }
  }

  getBreweries(){
    // Add call to populate beers from Grails controller
    // $.ajax({
    //   url: 'https://jsonplaceholder.typicode.com/todos',
    //   dataType: 'json',
    //   cache: false,
    //   success: function(data){
    //     this.setState({todos:data}, function() {
    //       console.log(this.state);
    //     });
    //   }.bind(this),
    //   error: function(xhr, status, err) {
    //     console.log(err);
    //   }
    // });
  }

  componentWillMount(){
    this.setState({ breweries: [
      {
        name: 'The Veil Brewing Co.',
        address: '1301 Roseneath Rd, Richmond, VA 23230'
      },
      {
        name: 'Triple Crossing Brewing',
        address: '113 S Foushee St, Richmond, VA 23220'
      },
      {
        name: 'Ardent Craft Ales',
        address: '3200 W Leigh St, Richmond, VA 23230'
      }

    ]});
    this.getBreweries();
  }

  componentDidMount(){
    this.getBreweries();
  }

  render() {
    return (
      <div className="App">
        <NotificationMenu />
        <Breweries breweries={this.state.breweries} onDelete={this.handleDeleteBrewery.bind(this)}/>
      </div>
    );
  }

  handleAddBrewery(brewery) {
    let breweries = this.state.breweries;
    breweries.push(brewery);
    this.setState({brewery:brewery})
  }

  handleDeleteBrewery(name) {
    let breweries = this.state.breweries;
    let index = breweries.findIndex(x => x.name === name);
    breweries.splice(index, 1);
    this.setState({breweries:breweries})
  }
}

export default App;
