//import logo from './logo.svg';
import './App.css';
import Menu from './componentes/Menu';
import Login from './componentes/Login'
import Logout from './componentes/Logout'
import Clientes from "./componentes/Cliente"
import Deposito from "./componentes/Deposito"
import Movimiento from "./componentes/Movimiento"
import Retiro from "./componentes/Retiro"

import {BrowserRouter, Route, Routes} from "react-router-dom"
function App() {
  return (
    <div className="App">
      <div className='App-header' >
        <Menu/>
      </div>
      <BrowserRouter>
         <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/logout" element={<Logout />} />
            <Route path="/cliente" element={<Clientes />} />
            <Route path="/deposito/:id" element={<Deposito />} />
            <Route path="/movimiento/:id" element={<Movimiento />} />
            <Route path="/retiro/:id" element={<Retiro />} />

          </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
