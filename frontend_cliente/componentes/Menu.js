const Menu = () => {
    return (

      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

      <div className="container-fluid">
      <div class="navbar-nav">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0 list-group-horizontal" >
          <li className="nav-item list-group-item" id="barra2">
            <a className="nav-link active" aria-current="page" href="/">
              Inicio
            </a>
          </li>
         
          <li className="nav-item list-group-item" id="barra2">
            <a className="nav-link" aria-current="page" href="/cliente">
             Operaciones
            </a>
          </li>
          <li className="nav-item list-group-item" id="barra2">
            <a className="nav-link" href="/login">
              Login
            </a>
          </li>
          <li className="nav-item list-group-item" id="barra2">
            <a className="nav-link" href="/logout">
              LogOut
            </a>
          </li>
        </ul>
      </div>
      </div>
   </nav>


    );
  };
  
  export default Menu;
