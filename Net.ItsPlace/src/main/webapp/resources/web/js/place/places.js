(function ($) {
	 // **Item class**: The atomic part of our Model. A model is basically a Javascript object, i.e. key-value pairs, with some helper functions to handle event triggering, persistence, etc.
  var Item = Backbone.Model.extend({
    defaults: {
      part1: 'hello1',
      part2: 'world'
    }
  });      
  
  // **List class**: A collection of `Item`s. Basically an array of Model objects with some helper functions.
  var lists = [{part1:'aaaaa',part2:'dddd'},
  				{part1:'aaDASDaaa',part2:'ddSADdd'}
  			  ];
  			  
   var lists = [];
  var List = Backbone.Collection.extend({
    model: Item,
    url:'/place/hellowWorld'
  });

  var ListView = Backbone.View.extend({
    el: $('#modal-test'),
    events: {
      'click button#add': 'addItem',
      'click button#remove': 'reItem'
    },
    // `initialize()` now instantiates a Collection, and binds its `add` event to own method `appendItem`. (Recall that Backbone doesn't offer a separate Controller for bindings...).
    initialize: function(){
      _.bindAll(this, 'render', 'addItem', 'appendItem','reItem','removeItem'); // remember: every function that uses 'this' as the current object should be in here
      
      this.collection = new List(lists);
      this.collection.fetch();
      this.collection.bind('add', this.appendItem); // collection event binder
      this.collection.bind('remove', this.removeItem); // collection event binder

      this.counter = 0;
      this.render();      
    },
    render: function(){
      // Save reference to `this` so it can be accessed from within the scope of the callback below
      var self = this;      
      $(this.el).append("<button id='add'>Add list item</button>");
      $(this.el).append("<button id='remove'>remove item</button>");
      $(this.el).append("<ul></ul>");
      _(this.collection.models).each(function(item){ // in case collection is not empty
        self.appendItem(item);
      }, this);
    },
    // `addItem()` now deals solely with models/collections. View updates are delegated to the `add` event listener `appendItem()` below.
    addItem: function(){
      this.counter++;
      var item = new Item();
      item.set({
        part2: item.get('part2') + this.counter // modify item defaults
      });
      this.collection.add(item); // add item to collection; view is updated via event 'add'
    },
    reItem: function(){
    	console.log('remove');
    	 var item = new Item();
      item.set({
        part2: item.get('part2') + this.counter // modify item defaults
      });
	   
	    this.collection.remove(item); // add item to collection; view is updated via event 'add'
	    console.log("xxx");
    },
    // `appendItem()` is triggered by the collection event `add`, and handles the visual update.
    appendItem: function(item){
      $('ul').append("<li>"+item.get('part1')+" "+item.get('part2')+"</li>");
    },
    removeItem: function(item){
    	console.log('ssssssssssssssssssss');
      $('ul').append("<li>ddddddd</li>");
    }
  });

  var listView = new ListView();
})(jQuery);