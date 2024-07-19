import {useState} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {BrowserRouter} from 'react-router-dom';
import OysterNavbar from "./OysterNavBar.jsx";

function App() {


    return (
        <>
            <BrowserRouter>
                <>
                    <div>
                        <OysterNavbar/>
                    </div>
                </>
            </BrowserRouter>
        </>
    )
}

export default App
