import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import OrderForm from './components/OrderForm';
import PayForm from './components/PayForm';
import './setupProxy';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<OrderForm/>}/>
                <Route path="/payment" element={<PayForm/>}/>
            </Routes>
        </Router>
    );
}

export default App;