import './App.css'
import {BrowserRouter} from 'react-router-dom';
import OysterNavbar from "./OysterNavBar.jsx";
import {useState} from "react";
import MainContent from "./MainContent.jsx";

export default function App() {
    const [responseMessage, setResponseMessage] = useState("");
    const [showStationInputForm, setShowStationInputForm] = useState(false);

    return (
        <>
            <BrowserRouter>
                <>
                    <div>
                        <OysterNavbar responseMessage={responseMessage}
                                      showStationInputForm={showStationInputForm}
                                      changeResponseMessage={(value) => setResponseMessage(value)}
                                      toggleStationInputForm={(value) => setShowStationInputForm(value)}
                        />
                        <MainContent responseMessage={responseMessage}
                                     showStationInputForm={showStationInputForm}
                                     changeResponseMessage={(value) => setResponseMessage(value)}
                                     toggleStationInputForm={(value) => setShowStationInputForm(value)}
                        />
                    </div>
                </>
            </BrowserRouter>
        </>
    )

}

