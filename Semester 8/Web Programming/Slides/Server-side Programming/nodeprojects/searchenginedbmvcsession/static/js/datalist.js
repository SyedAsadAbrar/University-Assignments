var datalistPool = new Array();

function DataList(ds){	
	this.dataSource = ds;
	this.paging = 'enabled';
	this.pageSize = 5;
	this.currentPage = 1;
	this.displayElementId = '';
	this.template = '<p>This is sample template</p>';
	this.id = this.generateId();
	datalistPool[this.id] = this;
	
	this.pagingtype = 'client';	
	this.serverPage = 1;
}

DataList.prototype.generateId = function(){
	var time = Date.now();
	var prefix = Math.floor((Math.random() * 10) + 1);
	var suffix = Math.floor((Math.random() * 10) + 1);
	
	return 'dl' + prefix + time + suffix;
}

DataList.prototype.getHtml = function(){
	var html = '<div class="datalist" id="' + this.id +'">';		
	html += this.getRowsDisplay();		
	html += '</div>';
	html += this.getPagerDisplay();
	return html;
}

DataList.prototype.display = function(divId){
	this.displayElementId = divId;	
	document.getElementById(divId).innerHTML = this.getHtml();		
}

DataList.prototype.updateRowsDisplay = function(){
	document.getElementById(this.id).innerHTML = this.getRowsDisplay();		
}

DataList.prototype.setTemplate = function(temp){
	this.template = temp;
}
	
DataList.prototype.getRowDisplay = function(data){	
	var html = this.template;
	for(x in data){
		html = html.replace('{'+x+'}',data[x]);
	}
	return html;
}

DataList.prototype.maxPages = function(){
	return Math.ceil(this.dataSource.length / this.pageSize);
}

DataList.prototype.setPageSize = function(ps){
	this.pageSize = ps;
}

DataList.prototype.getRowsDisplay = function(){
	
	var html = '';

	for(var i= this.getStartIndex();i < this.getEndIndex() && i < this.dataSource.length;i++){
		html += this.getRowDisplay(this.dataSource[i]);
	}

	return html;		
}

DataList.prototype.getStartIndex = function(){
	return (this.currentPage-1)*this.pageSize;
}

DataList.prototype.getEndIndex = function(){
	if (this.paging != "enabled"){
		return this.dataSource.length;
	}
	
	return this.currentPage*this.pageSize;
}


DataList.prototype.getPagerDisplay = function(id){
	var html = '';
	if(this.paging == 'enabled'){
		html = '<div class="datalistpager"> <a href="#" onclick="list_previous(\''+this.id+'\')">Previous</a> <a href="#" onclick="list_next(\''+this.id+'\')">Next</a> </div>';		
	}
	return html;
}

DataList.prototype.setPagingType = function(type){
	this.pagingtype = type;
}

DataList.prototype.setPage = function(page){
	if (this.pagingtype == 'server')
		this.serverPage = page;
}

DataList.prototype.nextPage = function(){
	if(this.pagingtype == 'client'){
		if(this.currentPage < this.maxPages()){
			this.currentPage++;
		}
		this.updateRowsDisplay();	
	}
	else {
		this.serverPage++;
		this.submitDocument();
	}
}

DataList.prototype.previousPage = function(){
	if(this.pagingtype == 'client'){
		if(this.currentPage > 1){
			this.currentPage--;
		}
		this.updateRowsDisplay();
	}
	else{
		this.serverPage--;
		this.submitDocument();
	}
}

DataList.prototype.submitDocument = function(){
	var input = document.createElement("input");
	input.setAttribute("type","hidden");
	input.setAttribute("name","page");
	input.setAttribute("value",this.serverPage);
	document.forms[0].appendChild(input);
	input = document.createElement("input");
	input.setAttribute("type","hidden");
	input.setAttribute("name","size");
	input.setAttribute("value",this.pageSize);
	document.forms[0].appendChild(input);
	document.forms[0].submit();
}

function list_next(id){	
	datalistPool[id].nextPage();
}

function list_previous(id){	
	datalistPool[id].previousPage();
}









// ----------------

var app = angular.module("datalistApp",[]);

app.directive("datalistWidget",function(){
	
	return {
		scope : false,
		link : function(scope,element,attrs){			
			scope.dl = new DataList(scope.datasource);
			scope.dl.setPagingType(attrs.pagetype);
			scope.dl.setPageSize(Number(attrs.pagesize));						
			scope.dl.setPage(attrs.page);
			scope.dl.setTemplate(scope.template);
			element.id = scope.dl.id;
			scope.dl.displayElementId = scope.dl.id;
			element.append(scope.dl.getHtml());
		}

	};
});

