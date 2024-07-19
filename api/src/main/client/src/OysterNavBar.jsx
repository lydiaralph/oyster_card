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

        const queryBackend = async ({url, method, userId}) => {
            fetch(`${url}`, {
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
            // })
        }

        return (
            <>
                <h1>Welcome to Oyster!</h1>
                <Navbar color="dark" dark expand="md">
                    <Nav className="justify-content-end" navbar>
                        <button onClick={() => queryBackend({url: "/balance?userId=User123", method: "GET", userId: "TEST_USER_ID"})}>
                            Check Balance
                        </button>

                        <button onClick={() => queryBackend({url: "/balance?userId=User123&amount=1.25", method: "PUT", userId: "TEST_USER_ID"})}>
                            Top Up Balance
                        </button>

                        <button onClick={() => queryBackend({url: "/tap?userId=User123&stationName=Holborn", method: "PUT", userId: "TEST_USER_ID"})}>
                            Tap (Tube)
                        </button>

                        <button onClick={() => queryBackend({url: "/tap?userId=User123&stationName=bus", method: "PUT", userId: "TEST_USER_ID"})}>
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
