import ExpoMaoadsModule from "./src/ExpoMaoadsModule";
import { EventEmitter, Subscription } from "expo-modules-core";
const emitter = new EventEmitter(ExpoMaoadsModule);

export function addRewardListener(listener: (event) => void): Subscription {
  return emitter.addListener("onReward", listener);
}

interface Maoads {
  splash(): void;
  reward(): void;
}

export { default as Banner, Props } from "./src/ExpoMaoadsView";

export default ExpoMaoadsModule as Maoads;
