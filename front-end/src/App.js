import './App.css';
import {Route, Routes} from "react-router-dom";
import {useLocalState} from "../../front-end/src/util/localStorage";
import Login from "../../front-end/src/components/login";
import Homepage from "../../front-end/src/components/homepage";
import PrivateRoute from "../../front-end/src/util/privateRoute";
import Configurator from "../../front-end/src/components/configurator";
import SignUp from "../../front-end/src/components/signUp";
import Navbar from "./util/navbar";
import UserPage from "./components/userPage";

function App() {
  const [jwt, setJwt] = useLocalState("", "jwt");

  return (
      <>
          <Navbar/>
          <Routes>
              <Route path="/configurator" element={
                  <PrivateRoute>
                      <Configurator/>
                  </PrivateRoute>
              }/>
              <Route path="/login" element={<Login/>} />
              <Route path="/" element={<Homepage/>}/>
              <Route path="/signup" element={<SignUp/>}/>
              <Route path="/user" element={
                  <PrivateRoute>
                      <UserPage/>
                  </PrivateRoute>
              }/>
          </Routes>
      </>
  );
}

export default App;
