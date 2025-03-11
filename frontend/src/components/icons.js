import L from 'leaflet';
import greenCircle from '../imgs/green-circle.svg';


const iconUser = new L.Icon({
    iconUrl: greenCircle,
    iconSize: new L.Point(40, 75)
});

export { iconUser };
