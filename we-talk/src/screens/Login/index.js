import { useNavigation } from '@react-navigation/core';
import React, { useState, useEffect } from 'react';
import {
    View,
    TextInput,
    StyleSheet,
    Pressable,
    Text,
    Alert,
} from 'react-native';


const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigation = useNavigation();
    const redirectHome = () => {
        navigation.reset({
            index: 0,
            routes: [
                {
                    name: 'Contacts',
                },
            ],
        });
    };


    return (
        <View style={styles.page}>
            <Text style={styles.titleText} >
                We Talk
                {"\n"}
                {"\n"}
            </Text>
            <TextInput
                value={username}
                onChangeText={setUsername}
                placeholder="username"
                style={styles.input}
                autoCapitalize="none"
            />
            <TextInput
                value={password}
                onChangeText={setPassword}
                placeholder="password"
                style={styles.input}
                secureTextEntry
            />

            <Pressable style={styles.button} onPress={() => redirectHome()}>
                <Text style={{ color: 'white' }}>Sign in</Text>
            </Pressable>

        </View>
    );
};

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
        backgroundColor: '#3F96D0',
        padding: 10,
        marginVertical: 10,
        borderRadius: 5,
        alignItems: 'center',
    },
    titleText: {
        color: '#3F96D0',
        fontSize: 20,
        fontWeight: "bold",
        textAlign: "center",
        textTransform: 'uppercase',

    }
});

export default Login;
