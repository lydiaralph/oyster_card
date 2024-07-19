import React, {useState} from 'react';
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink} from 'reactstrap';
import {Link} from 'react-router-dom';

const OysterNavbar = () => {

    const [isOpen, setIsOpen] = useState(false);
    const [count, setCount] = useState(0)
    const [balance, setBalance] = useState(0);
    const [balanceFetched, setBalanceFetched] = useState(false);

    return (
        <>
            <h1>Welcome to Oyster!</h1>
            <Navbar color="dark" dark expand="md">
                <Nav className="justify-content-end" navbar>
                    <button onClick={() => {
                        fetch('/balance')
                            .then(response => response.json())
                        then(data => {
                            setBalance(data);
                            setBalanceFetched(true);
                        })
                    }}
                    >Top Up
                    </button>
                    <button onClick={() => setCount((count) => count + 1)}>
                        Check Balance
                    </button>
                    <button onClick={() => setCount((count) => count + 1)}>
                        Tap (Tube)
                    </button>
                    <button onClick={() => setCount((count) => count + 1)}>
                        Tap (Bus)
                    </button>
                </Nav>
            </Navbar>
        </>
    );
};

export default OysterNavbar;
