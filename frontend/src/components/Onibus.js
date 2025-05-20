import React from "react";
import { Marker, Popup } from "react-leaflet";
import { iconUser } from "./icons";

const Onibus = ( {obj} ) => {
  const data = new Date(obj[1][0].data)
  var diff = new Date() - data;
  var diffMins = Math.round(((diff % 86400000) % 3600000) / 60000); // minutes
  return (

    <Marker position={[obj[1][0].lat, obj[1][0].lon]}>
      <Popup>
        {obj[1][0].descricao}
        <br />
        <br />

        Última atualização há {diffMins} minutos
      </Popup>
    </Marker>
  );
}

export default Onibus;
