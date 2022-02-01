/**
 * @format
 */

import { AppRegistry } from 'react-native';
import App from './src/App';
import { name as appName } from './app.json';
import { BackgroundTaskCallback } from "./src/native_modules/BackgroundTaskCallback"

AppRegistry.registerHeadlessTask('BackgroundTaskHeadlessJs', () => BackgroundTaskCallback);

AppRegistry.registerComponent(appName, () => App);
