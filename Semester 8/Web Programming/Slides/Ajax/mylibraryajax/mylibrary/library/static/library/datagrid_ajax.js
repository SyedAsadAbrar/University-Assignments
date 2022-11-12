function DataGrid(url){
	
	this.srcUrl = url;
	this.header = "";
	this.dataSource = "";
	this.filters = [];

	this.paging = "enabled";
	this.pageSize = 5;
	this.currentPage = 1;
	this.maxPages = 1;

	this.divId = "";


	this.setPageSize = function(ps){
		this.pageSize = ps;
	}

	this.addFilter = function(key,value){
		this.filters[key] = value;
	}

	this.load = function(){
		var url = this.srcUrl;

		if(this.paging == "enabled"){
			url = url + "?pagesize=" + this.pageSize + "&page=" + this.currentPage;
		}

		for(var filter in this.filters){
			url = url + "&" + filter + "=" + this.filters[filter];
		}

		var ajaxRequest = new ajax("GET",url,true,this.loadHandler,this);
		ajaxRequest.send();		
	}

	this.loadHandler = function(response,responseXml,caller){

		// load header
		caller.maxPages = responseXml.getElementsByTagName("maxPages")[0].textContent;

		// load header
		var columns = responseXml.getElementsByTagName("column");

		caller.header = new Array();
		for(i=0; i < columns.length; i++){
			caller.header[i] = columns[i].textContent;
		}

		// load data
		caller.datasource = new Array();
		var rows = responseXml.getElementsByTagName("row");
		for(i=0; i < rows.length; i++){
			var row = rows[i];

			var rowEntry = new Array();			
			for(j=0; j < caller.header.length; j++){
				rowEntry[j] = row.getElementsByTagName(caller.header[j])[0].textContent;
			}
			caller.datasource[i] = rowEntry;
		}


		caller.displayHandler();

	}


	this.display = function(div){
		this.divId = div;
		this.load();
	}

	this.displayHandler = function(){

		var html = '<table class="grid">';
		html += this.getHeaderDisplay();
		html += this.getRowsDisplay();
		html += '</table>';

		document.getElementById(this.divId).innerHTML = html;

		this.displayPager(this.divId);
		this.displayElementId = this.divId;
	}

	this.getHeaderDisplay = function(){
		var html = '<tr>';
		for(var i=0; i < this.header.length;i++){
			html+= '<th>' + this.header[i] + '</th>';
		}
		html+= '</tr>';
		return html;
	}

	this.getRowsDisplay = function(){
		var html = '';
		for(var i=0; i < this.datasource.length;i++){
			html+= '<tr>';
			for(var j=0; j < this.datasource[i].length; j++){
				html+= '<td>' + this.datasource[i][j] + '</td>';
			}
			html+= '</tr>';
		}
		return html;			
	}

	
	this.displayPager = function(id){
		if(this.paging == 'enabled'){
			var html = '<div class="gridPager"> <a href="#" onclick="grid_previous()">Previous</a> <a href="#" onclick="grid_next()">Next</a> </div>';
			document.getElementById(id).innerHTML += html;
		}
	}

	this.nextPage = function(){
		if(this.currentPage < this.maxPages){
			this.currentPage++;
		}
		this.display(this.displayElementId);
	}

	this.previousPage = function(){
		if(this.currentPage > 1){
			this.currentPage--;
		}
		this.display(this.displayElementId);
	}


}

