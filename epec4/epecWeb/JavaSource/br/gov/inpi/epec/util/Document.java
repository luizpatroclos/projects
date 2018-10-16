package br.gov.inpi.epec.util;

	
	import java.io.Serializable;
	 
	public class Document implements Serializable, Comparable<Document> {
	 

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String name;
	     
	    private String size;
	     
	    private String type;
	    
	    private String path;
	     
	    public Document(String name, String size, String type, String path) {
	        this.name = name;
	        this.size = size;
	        this.type = type;
	        this.path = path;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
	    public String getSize() {
	        return size;
	    }
	 
	    public void setSize(String size) {
	        this.size = size;
	    }
	 
	    public String getType() {
	        return type;
	    }
	 
	    public void setType(String type) {
	        this.type = type;
	    }
	    
	    public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
	 
	    //Eclipse Generated hashCode and equals
	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((name == null) ? 0 : name.hashCode());
	        result = prime * result + ((size == null) ? 0 : size.hashCode());
	        result = prime * result + ((type == null) ? 0 : type.hashCode());
	        result = prime * result + ((path == null) ? 0 : path.hashCode());
	        return result;
	    }
	 
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Document other = (Document) obj;
	        if (name == null) {
	            if (other.name != null)
	                return false;
	        } else if (!name.equals(other.name))
	            return false;
	        if (size == null) {
	            if (other.size != null)
	                return false;
	        } else if (!size.equals(other.size))
	            return false;
	        if (type == null) {
	            if (other.type != null)
	                return false;
	        } else if (!type.equals(other.type))
	            return false;
	        if (path == null) {
	            if (other.path != null)
	                return false;
	        } else if (!path.equals(other.path))
	            return false;
	        
	        return true;
	    }
	 
	    @Override
	    public String toString() {
	        return name;
	    }
	    
	    public String toPath() {
	        return path;
	    }
	 
	    public int compareTo(Document document) {
	        return this.getName().compareTo(document.getName());
	    }

		
	}  

