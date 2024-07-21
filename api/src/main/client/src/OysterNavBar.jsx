import React from 'react';
import {Nav, Navbar} from 'reactstrap';
import NavButtons from "./NavButtons.jsx";

function OysterNavbar({responseMessage, showStationInputForm, changeResponseMessage, toggleStationInputForm}) {
    return (
        <>
            <h1>Welcome to Oyster!</h1>
            <Navbar color="dark" dark expand="md">
                <Nav className="justify-content-end" navbar>
                    <NavButtons responseMessage={responseMessage}
                                showStationInputForm={showStationInputForm}
                                changeResponseMessage={changeResponseMessage}
                                toggleStationInputForm={toggleStationInputForm}
                    />
                </Nav>
            </Navbar>

        </>
    );
}
;

export default OysterNavbar;
