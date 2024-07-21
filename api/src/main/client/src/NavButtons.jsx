import React from "react";

function NavButtons({ responseMessage, showStationInputForm, changeResponseMessage, toggleStationInputForm }) {
    const TEST_USER_ID = "User123";

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
                toggleStationInputForm(false)
                changeResponseMessage(data.response);
            });
    }

    return (
        <>
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
                toggleStationInputForm(true)
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
        </>
    );

}

export default NavButtons
