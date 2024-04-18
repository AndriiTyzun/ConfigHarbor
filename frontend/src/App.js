import './App.css';
import {Route, Routes} from "react-router-dom";
import {useLocalState} from "./util/localStorage";
import Login from "./components/login";
import Homepage from "./components/homepage";
import PrivateRoute from "./util/privateRoute";
import Configurator from "./components/configurator";


function App() {
    const [jwt, setJwt] = useLocalState("", "jwt");

    return (
      <Routes>
          <Route path="/configurator" element={
             <PrivateRoute>
                 <Configurator/>
             </PrivateRoute>
          }/>
          <Route path="/login" element={<Login/>} />
          <Route path="/" element={<Homepage/>}/>
      </Routes>
    );
}

export default App;
