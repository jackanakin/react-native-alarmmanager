import { AppState } from "react-native";

export default async function run() {
    const state = AppState.currentState;
    if (state === "background") {
        console.log("Running task bc i'm in background");
    } else {
        console.log("I'm in foreground and not going to run");
    }
}

export const BackgroundTaskCallback = async () => {
    try {
        run();
    } catch (err) { }
};