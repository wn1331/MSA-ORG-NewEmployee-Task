import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './setupProxy';
import 'bootstrap/dist/css/bootstrap.css';
import HomeForm from "./components/HomeForm";
import OrderForm from "./components/OrderForm";
import PayForm from "./components/PayForm";
import DashBoard from "./components/DashBoard";

function App() {
    return (
        <Router>
            <Routes>
                <Route path={"/"} element={<HomeForm/>}/>
                <Route path={"/order"} element={<OrderForm/>}/>
                <Route path={"/payment"} element={<PayForm/>}/>
                <Route path={"/dashboard"} element={<DashBoard/>}/>
            </Routes>
        </Router>
    );
}

export default App;