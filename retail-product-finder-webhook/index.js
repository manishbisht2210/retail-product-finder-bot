/*
 * ******************************************************
 *  * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *  *
 *  * This file is part of Team Outliers.
 *  *
 *  * Team Outliers can not be copied and/or distributed without the express
 *  * permission of Team Outliers
 *  ******************************************************
 */
'use strict';
// call the packages we need
const express = require('express');
const bodyParser = require('body-parser');
const fs = require("fs");
const app = express();

//app's config
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies
const server = app.listen(process.env.PORT || 3000, () => {
	console.log('Express server listening on port %d in %s mode', server.address().port, app.settings.env);
});

//Simple get call
app.get('/', (request, response) => {
	response.send('Hello, This is Retail Product Finder DialogFlow WebHook')
});

// Triggered by a POST to /webhook from Dialog Flow
app.post('/webhook', (request, response) => {
	var data = request.body;
	console.log(data['queryResult']);
	// An action is a string used to identify what tasks needs to be done
	// in fulfillment usually based on the corresponding intent.
	var action = data["queryResult"]["action"]
	// Parameters are any entites that DialogFlow API has extracted from the request.
	const parameters = data["queryResult"]["parameters"]
	if (action != null) {
		handleAction(action, request, response, parameters);
	} else {
		response.json({ "speech": "This is the default case from Webhook" });
	}
});

//For handling actions
function handleAction(action, request, response, parameters) {
	switch (action) {
		case 'callBotAction':
			callBot(request, response, parameters);
			break;
		default:
			console.log("unknownAction");
	}
}

function initializeSpeech() {
	var speech = "Dear, Please follow your bot. It will guide you to reach the requested product :";
	return speech;
}

function callBot(request, response, parameters) {
	var productName = parameters['Product_Name'];
	executeGet(productName);
	var speech = initializeSpeech();
	var outputBody = {
		"fulfillmentText": speech + " " + productName
	}
	response.json(outputBody);
}
// Bot Navigation Service Call 
function executeGet(productName) {
	var Request = require("request");
	Request.get({
		"url": 'https://b9fa63f4.ngrok.io/retail-product-finder-service/api/v1/navigation/searchAndControlBot?deviceId=bot_001&productName=' + productName,
		"headers": {
			'Authorization': 'Bearer '
		},
		"rejectUnauthorized": false
	}, (error, response,body) => {
		if (error) {
			return console.dir(error);
		}
		console.dir(body);
	});
}


