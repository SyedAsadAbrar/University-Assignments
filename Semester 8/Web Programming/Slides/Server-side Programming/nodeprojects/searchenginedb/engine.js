var events = require('events');
var eventEmitter = new events.EventEmitter();

var MongoClient = require('mongodb').MongoClient;
var connection = 'mongodb://localhost:27017/';


class Resource{
	constructor(t,u,d){
		this.title = t;
		this.url = u;
		this.description = d;
	}
	
	generateHtml(){
		var html = "<p>";
		html += "<a href='" + this.url + "'>" + this.title + "</a>";
		html += "<br/>";
		html += "<p>" + this.description + "</p>";
		
		return html;
	}
}


class Index{
	constructor(){
		
		this.resources = [];
		this.index = [];
		this.status = "indexnotloaded";
		
		/*
		this.resources[0] = new Resource('Yahoo','https://www.yahoo.com','Yahoo Search Engine');
		this.resources[1] = new Resource('Google','https://www.google.com','Google Search Engine');
		this.index['yahoo'] = [this.resources[0]];
		this.index['google'] = [this.resources[1]];
		this.index['search engine'] = [this.resources[0],this.resources[1]];
		*/
		
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
		
		MongoClient.connect(connection, function(err, db) {
		  if (err) throw err;
		  console.log("Database created!");
		  var dbo = db.db("searchengine");
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
		
		MongoClient.connect(connection, function(err, db) {
		  if (err) throw err;
		  console.log("Database created!");
		  var dbo = db.db("searchengine");
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