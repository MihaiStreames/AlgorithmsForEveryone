import os
import sys

sys.path.insert(0, os.path.abspath('../src'))

# -- Project information -----------------------------------------------------
project = 'AlgorithmsForEveryone'
author = 'MihaiStreames, [Name2], [Name3]'
copyright = '2025, AlgorithmsForEveryone'
release = '0.1.0'

# -- General configuration ---------------------------------------------------
extensions = [
    'sphinx.ext.autodoc',  # Core: pulls in docstrings
    'sphinx.ext.napoleon',  # Support for Google/NumPy docstring style
    'sphinx.ext.viewcode',  # Add "View Source" links
    'sphinx.ext.autosummary',  # Auto-generate summary tables for modules/classes
]

# Generate autosummary pages automatically
autosummary_generate = True

# Show class members, etc. automatically
autodoc_default_options = {
    'members': True,
    'undoc-members': True,
    'show-inheritance': True,
    'inherited-members': True
}

# Napoleon settings (if you want to tweak Google vs NumPy style)
napoleon_google_docstring = True
napoleon_numpy_docstring = False

templates_path = ['_templates']
exclude_patterns = []

# -- Options for HTML output -------------------------------------------------
html_theme = 'alabaster'
html_static_path = ['_static']
pygments_style = 'friendly'
