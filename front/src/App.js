import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './setupProxy';
import 'bootstrap/dist/css/bootstrap.css';
import HomeForm from "./components/HomeForm";
import NewOrderForm from "./components/NewOrderForm";
import NewPayForm from "./components/NewPayForm";
import DashBoard from "./components/DashBoard";

function App() {
    return (
        <Router>
            <Routes>
                <Route path={"/"} element={<HomeForm/>}/>
                <Route path={"/order"} element={<NewOrderForm/>}/>
                <Route path={"/payment"} element={<NewPayForm/>}/>
                <Route path={"/dashboard"} element={<DashBoard/>}/>
            </Routes>
        </Router>
    );
}

export default App;