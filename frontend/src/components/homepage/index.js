import {useLocalState} from "../../util/localStorage";

const Homepage = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");

    function signUp() {
        fetch("api/users", {
            method: "POST",
            headers : {
                "Content-Type": "application/json",
                "Authorization" : `Bearer ${jwt}`
            }
        }).then((response) => {
            if (response.status === 200) {
                return response.json();
            }
        }).then((data) => {
            console.log(data)
        })
    }

    return (
        <div>

            <div>
                Homepage!
                <br/>
                JWT is {jwt}
            </div>
            <div>
                <button onClick={() => signUp()}>Sign Up!</button>
            </div>

        </div>
    );
};

export default Homepage;