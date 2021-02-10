# How does one make a syntax file? - Cy4Shot

Well, here is the syntax!

```json
{
	"extension": "file extension (e.g. 'java')",
	"css_path": "path to css (e.g. 'styles/syntax/java'",
	"rules": [
		{
			"regex": "here you put the regex defining the colour",
			"type": "here you put the type of the colour which is defined in the css (e.g. 'keyword')"
		},
		{
			"regex": "this is another rule, the rules will be read in order top to bottom",
			"type": "comment"
		}
	]

}
```

And that is it for the json! But we also have css. In here we define the colours per type of regex.

```css
.keyword {
    -fx-fill: purple;
    -fx-font-weight: bold;
}

.comment {
    -fx-fill: purple;
}
```