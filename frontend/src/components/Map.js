import React, { useState, useContext, useEffect } from "react";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import User from "./User";
import SocketClientContext from "../context/SocketClientContext";

const Map = () => {
  const { socketClient } = useContext(SocketClientContext);
  const [positions, setPositions] = useState({});

  useEffect(() => {
    let subscription = socketClient.subscribe('/topic/bus', data => {
      console.log(data.body);
      setPositions(JSON.parse(data.body));
    });

    return () => {
      if (subscription) {
        subscription.unsubscribe();
      }
    };
  },  [socketClient]);

  return (
    <MapContainer center={[-19.922859, -43.945156]} zoom={25} scrollWheelZoom={true}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap<a/> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <User />
      {

       Object.entries(positions).map((obj, i) => {
          return (

            <Marker position={[obj[1][0].lat, obj[1][0].lon]}>
              <Popup>
                {obj[1][0].descricao}:{obj[1][0].data}
              </Popup>
            </Marker>
          );
        })
      }
    </MapContainer>
  );
}

export default Map;
