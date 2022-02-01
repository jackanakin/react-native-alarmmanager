import { NativeModules } from 'react-native';

const { BackgroundTaskModule } = NativeModules;

interface IBackgroundTaskModule {
    scheduleTask(milisecondsInterval: number): Promise<string>;
    cancelTask(): void;
}

export default BackgroundTaskModule as IBackgroundTaskModule;
