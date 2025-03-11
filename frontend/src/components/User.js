import React, { useState, useEffect } from "react";
import { Marker } from "react-leaflet";
import { useMap } from 'react-leaflet/hooks'
import { iconUser } from "./icons";

const User = () => {
  const [location, setLocation] = useState({ latitude: -19.922859, longitude: -43.945156 }); //Praça Raul Soares
  const map = useMap();

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position => setLocation({
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          }),
        err => console.log(err.message)
      );
      map.setView([location.latitude, location.longitude], 25);
    } else {
      console.log("Geolocation is not supported by this browser.");
    }
  }, []);

  return (<Marker position={[location.latitude, location.longitude]} icon={iconUser}/>);
}

export default User;
