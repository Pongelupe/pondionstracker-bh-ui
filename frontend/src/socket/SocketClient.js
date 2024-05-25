import { Client } from "@stomp/stompjs";

class SocketClient {
  constructor(url) {
    this.url = url;
    this.client = new Client({
      brokerURL: url,
      onConnect: () => {
        console.log("connected!")
      } 

    });

    this.client.activate();

  }

  deactivate = () => {
    this.client.deactivate();
  };

  subscribe = (topic, callback) => {
   return this.client.subscribe(topic, (message) => {
      callback(message);
    });
  };

awaitConnect = async (awaitConnectConfig) => {
    const {
      retries = 3,
      curr = 0,
      timeinterval = 100,
    } = awaitConnectConfig || {};
    return new Promise((resolve, reject) => {
      console.log(timeinterval);
      setTimeout(() => {
        if (this.connected) {
          resolve();
        } else {
          console.log("failed to connect! retrying");
          if (curr >= retries) {
            console.log("failed to connect within the specified time interval");
            reject();
          }
          this.awaitConnect({ ...awaitConnectConfig, curr: curr + 1 });
        }
      }, timeinterval);
    });
  };

  get connected() {
    return this.client.connected;
  }

}

export default SocketClient;
