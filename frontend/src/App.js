import { useEffect, useState } from "react";
import './App.css';
import Map from './components/Map';
import SocketClientContext from "./context/SocketClientContext";
import SocketClient from "./socket/SocketClient";

function App() {

  const [socketClientContext, setSocketClientContext] = useState({});
  const [connected, setConnected] = useState(false);

  useEffect(() => {
    const connect = async () => {
      const socketClient = new SocketClient("ws://localhost:8080/pbh-onibus-websocket"); 
      await socketClient.awaitConnect();
      setSocketClientContext({ socketClient });
      setConnected(socketClient.connected);
    }
    connect();
  }, [])


  return (
    <div className="App">
      <SocketClientContext.Provider value={socketClientContext}>
        { connected ?  (<Map />) : (<p>connecting...</p>)}
      </SocketClientContext.Provider>
    </div>
  );
}

export default App;
