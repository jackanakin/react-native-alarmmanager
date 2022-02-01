/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  Button
} from 'react-native';
import BackgroundTaskModule from './native_modules/BackgroundTaskModule';

const App = () => {
  return (
    <SafeAreaView>
      <StatusBar />
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <Button title='SCHEDULE' onPress={() => BackgroundTaskModule.scheduleTask(60000)} />
        <Button title='CANCEL' onPress={() => BackgroundTaskModule.cancelTask()} />
      </ScrollView>
    </SafeAreaView>
  );
};


export default App;
