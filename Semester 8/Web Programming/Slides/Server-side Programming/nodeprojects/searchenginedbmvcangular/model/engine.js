var events = require('events');
var config = require('../config/settings');
var eventEmitter = new events.EventEmitter();

var MongoClient = require('mongodb').MongoClient;

class Resource{
	constructor(t,u,d){
		this.title = t;
		this.url = u;
		this.description = d;
	}
	
	toJson(){
		var json = '{';
		json += '"title":"'+ this.title + '",';
		json += '"url":"'+ this.url + '",';
		json += '"description":"'+ this.description + '"';		
		json += '}';
		
		
		return json;
	}
}


class Index{
	constructor(){
		
		this.resources = [];
		this.index = [];
		this.status = "indexnotloaded";
					
		this.load();
		
	}
	
	search(keyword,cb){		
		var searchActions = function(){
			var resources = this.index[keyword];
			if (resources == undefined)
				resources = [];
			cb(resources);
		}
		
		searchActions = searchActions.bind(this);
	
		if (this.status == "indexloaded"){
			searchActions();
		}
		else{
			this.on("indexloaded",searchActions);
		}
	}
	
	loadResources(){
		this.resourceObjects = new Array();
		var loadResourcesHelper = function(err,result){		  
  		    if (err) throw err;
			for(var c=0; c < result.length; c++){				  
				this.resources[c] = new Resource(result[c].title,result[c].url,result[c].description);
				this.resourceObjects[result[c]._id] = this.resources[c];
			}
			
			this.loadMap();
		}
		
		loadResourcesHelper = loadResourcesHelper.bind(this);
		
		MongoClient.connect(config.connection, function(err, db) {
		  if (err) throw err;
		  console.log("Database created!");
		  var dbo = db.db(config.db);
		  dbo.collection("resources").find({}).toArray(loadResourcesHelper);
		  db.close();
		});
		
	}
	
	loadMap(){
		
		var loadMapHelper = function(err,result){			
			if(err) throw err;
			
			for(var i=0; i < result.length; i++){
				if (this.index[result[i].key] == undefined){
					this.index[result[i].key] = new Array();
				}
				
				this.index[result[i].key].push(this.resourceObjects[result[i].resource]);
			}
			
			this.status = "indexloaded";
			eventEmitter.emit("indexloaded");
	
						
		}
		
		
		loadMapHelper = loadMapHelper.bind(this);
		
		MongoClient.connect(config.connection, function(err, db) {
		  if (err) throw err;
		  console.log("Database created!");
		  var dbo = db.db(config.db);
		  dbo.collection("map").find({}).toArray(loadMapHelper);
		  db.close();
		});				
		
	}
	
	
	load(){
			
		this.loadResources();
		
	}
	
	on(e,cb){
		eventEmitter.on(e,cb);
	}
}

module.exports = {
	Resource : Resource,
	Index : Index
};