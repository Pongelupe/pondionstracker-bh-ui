import { Client } from "@stomp/stompjs";

class SocketClient {
  constructor(url) {
    this.url = url;
    this.client = new Client({
      brokerURL: url,
      onConnect: () => {
        console.log("connected!")
        this.client.subscribe('/topic/bus', data => {
          console.log(data);
        })
      } 

    });

    this.client.activate();

  }

  activate = () => this.client.activate();

  deactivate = () => {
    this.client.deactivate();
  };

  subscribe = (topic, callback) => {
    return this.client.subscribe(topic, (message) => {
      callback(message);
    });
  };

  get connected() {
    return this.client.connected;
  }

}

export default SocketClient;
