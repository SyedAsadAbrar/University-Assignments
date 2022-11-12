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
		
		this.resources[0] = new Resource('Yahoo','https://www.yahoo.com','Yahoo Search Engine');
		this.resources[1] = new Resource('Google','https://www.google.com','Google Search Engine');
		this.index['yahoo'] = [this.resources[0]];
		this.index['google'] = [this.resources[1]];
		this.index['search engine'] = [this.resources[0],this.resources[1]];
	}
	
	search(keyword){
		var resources = this.index[keyword];
		if (resources == undefined)
			resources = [];
		return resources;
	}
}

module.exports = {
	Resource : Resource,
	Index : Index
};