import React, {useState} from 'react';
import {Nav, Navbar} from 'reactstrap';

const OysterNavbar = () => {

        const TEST_USER_ID = "User123";

        const [responseMessage, setResponseMessage] = useState("");

        const renderContentBox = () => {
            if (responseMessage !== "") {
                console.log(`Rendering content box with message ${responseMessage}`)
                return (<p className="main-content">{responseMessage}</p>);
            }
            return null
        };

        const queryBackend = async ({url, method, params}) => {
            fetch(`${url}?${params}`, {
                method: `${method}`,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
                .then(response => response.json())
                .then(data => {
                    setResponseMessage(data.response);
                    renderContentBox(data.response)
                });
        }

        return (
            <>
                <h1>Welcome to Oyster!</h1>
                <Navbar color="dark" dark expand="md">
                    <Nav className="justify-content-end" navbar>
                        <button onClick={() => {
                            const params = new URLSearchParams();
                            params.append('userId', `${TEST_USER_ID}`);
                            queryBackend({url: "/balance", method: "GET", params: params})
                        }}
                        >
                            Check Balance
                        </button>

                        <button onClick={() => {
                            const params = new URLSearchParams();
                            params.append('userId', `${TEST_USER_ID}`);
                            params.append('amount', '1.25');
                            queryBackend({url: "/balance", method: "PUT", params: params})
                        }}>
                            Top Up Balance
                        </button>

                        <button onClick={() => {
                            const params = new URLSearchParams();
                            params.append('userId', `${TEST_USER_ID}`);
                            params.append('stationName', 'Holborn');
                            queryBackend({url: "/tap", method: "PUT", params: params})
                        }}>
                            Tap (Tube)
                        </button>

                        <button onClick={() => {
                            const params = new URLSearchParams();
                            params.append('userId', `${TEST_USER_ID}`);
                            params.append('stationName', 'bus');
                            queryBackend({url: "/tap", method: "PUT", params: params})
                        }}>
                            Tap (Bus)
                        </button>
                    </Nav>
                </Navbar>
                {renderContentBox(responseMessage)}
            </>
        );
    }
;

export default OysterNavbar;
