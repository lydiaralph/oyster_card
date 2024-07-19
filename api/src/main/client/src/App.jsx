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
