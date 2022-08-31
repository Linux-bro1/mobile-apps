import React from 'react';
import {
  SafeAreaView, StatusBar, View,
  TextInput,
  StyleSheet,
  Pressable,
  Text,
  Alert
} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import Navigation from './src/navigation/index';


const App = () => {
  const styles = StyleSheet.create({
    page: {
      padding: 10,
      alignItems: 'stretch',
      flex: 1,
      justifyContent: 'center',
    },
    input: {
      backgroundColor: 'white',
      padding: 10,
      marginVertical: 10,
      borderRadius: 5,
    },
    button: {
      backgroundColor: 'dodgerblue',
      padding: 10,
      marginVertical: 10,
      borderRadius: 5,
      alignItems: 'center',
    },
    titleText: {
      fontSize: 20,
      fontWeight: "bold",
      textAlign: 'center',
      textTransform: 'uppercase',
      color: '#3F96D0',
    }
  });
  return (
    <>
      <StatusBar barStyle={'default'} />
      <Navigation />
    </>
  );

};

export default App;