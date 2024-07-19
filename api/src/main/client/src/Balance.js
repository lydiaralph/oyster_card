import React, {useEffect, useState} from 'react';
import {TextField} from "@mui/material";

const Balance = () => {

    const [balanceFetched, setBalanceFetched] = useState(false);
    const [balance, setBalance] = useState(0);

    useEffect(() => {
        fetch('balance')
            .then(response => response.json())
            .then(data => {
                setBalance(data);
                setBalanceFetched(true);
            })
    }, []);

    return (
        {balanceFetched} ? <div>
            <>
                <TextField>{balance}</TextField>
            </>
        </div> : null

    );
};

export default Balance;
