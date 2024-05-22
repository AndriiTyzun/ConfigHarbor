import {useLocalState} from "../../util/localStorage";
import {Link} from "react-router-dom";

const Homepage = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    return (
        <div>

            <div>
                Homepage!
                <br/>
                JWT is {jwt}
            </div>
            <div>
                <button>
                    <Link to={"/signup"}>SignUp</Link>
                </button>
            </div>

        </div>
    );
};

export default Homepage;