import { useEffect, useState } from "react";
import './App.css';
import Map from './components/Map';
import SocketClientContext from "./context/SocketClientContext";
import SocketClient from "./socket/SocketClient";

function App() {

  const [socketClientContext, setSocketClientContext] = useState({});

  useEffect(() => {
    const socketClient = new SocketClient("ws://localhost:8080/pbh-onibus-websocket"); 
    setSocketClientContext({ socketClient });
  }, [])


  return (
    <div className="App">
      <SocketClientContext.Provider value={socketClientContext}>
        <Map />
      </SocketClientContext.Provider>
    </div>
  );
}

export default App;
