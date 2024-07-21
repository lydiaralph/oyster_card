import React from "react";

function handleSubmit(e) {
    // Prevent the browser from reloading the page
    e.preventDefault();

    const TEST_USER_ID = "User123";

    const stationNameInput = document.getElementById("stationInputForm").stationName.value

    const params = new URLSearchParams();
    params.append('userId', `${TEST_USER_ID}`);
    params.append('stationName', stationNameInput);

    fetch(`tap?${params}`, {
        method: `PUT`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            changeResponseMessage(data.response);
            toggleStationInputForm(false)
        });
}


function MainContent({responseMessage, showStationInputForm, changeResponseMessage, toggleStationInputForm}) {
    if (showStationInputForm) {
        console.log("Rendering station input box")
        return (
            <p className="main-content">
                <form id="stationInputForm" method="put" onSubmit={handleSubmit}>
                    <label>
                        Station name: <input name="stationName"/>
                    </label>
                    <button type="submit">Submit form</button>
                </form>
            </p>);
    }

    if (responseMessage !== "") {
        console.log(`Rendering content box with message ${responseMessage}`)
        return (<p className="main-content">{responseMessage}</p>);
    }
    return null
}

export default MainContent
