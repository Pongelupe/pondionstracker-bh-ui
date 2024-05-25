import React, { useContext, useEffect } from "react";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import SocketClientContext from "../context/SocketClientContext";

const Map = () => {


  return (
    <MapContainer center={[-19.922859, -43.945156]} zoom={15} scrollWheelZoom={true}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap<a/> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Marker position={[-19.922859, -43.945156]}>
        <Popup>
          Praça Raul Soares
        </Popup>
      </Marker>
    </MapContainer>
  );
}

export default Map;
