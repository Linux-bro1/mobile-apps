import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';


import LoginScreen from '../screens/Login';
import ContactsScreen from '../screens/Contacts';
import CallingScreen from '../screens/Calling';

const Stack = createNativeStackNavigator();

const Navigation = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator screenOptions={{ headerShown: false }}>
                <Stack.Screen name="Login" component={LoginScreen} />
                <Stack.Screen name="Contacts" component={ContactsScreen} />
            </Stack.Navigator>
            <Stack.Group screenOptions={{ headerShown: false }}>
                <Stack.Screen name="Calling" component={CallingScreen} />
            </Stack.Group>
        </NavigationContainer>
    );
};

export default Navigation;
