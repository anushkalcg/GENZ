import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import Login from './components/Login'
import Signup from './components/Signup'
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Play from './components/Play'
import Quiz from './components/Quiz'
import CreateLeague from './components/CreateLeague';
import Join from './components/Join';
import OddAccumulator from './components/OddAccumulator'

const options = {
    position: 'bottom center',
    timeout: 5000,
    offset: '30px',
    transition: 'scale'
  }
ReactDOM.render(
    <BrowserRouter>
        <div>
            <Switch>
                <Route exact path='/' component={App}/>
                <Route path='/play/:id' component={Play}/>
                <Route path='/quiz/:groupInfo' component={Quiz}/>
                <Route path='/login' component={Login}/>
                <Route path='/signup' component={Signup}/>
                <Route path='/create/:id' component={CreateLeague}/>
                <Route path='/join/:id' component={Join}/>
                <Route path='/odds' component={OddAccumulator}/>
                <Route component={Error}/>
            </Switch>

        </div>
    </BrowserRouter>
    , document.getElementById('root')
);
