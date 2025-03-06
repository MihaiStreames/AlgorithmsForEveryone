# Configuration file for the Sphinx documentation builder
import os
import sys

sys.path.insert(0, os.path.abspath('../src'))

project = 'AlgorithmsForEveryone'
copyright = '2025, AlgorithmsForEveryone'
author = 'MihaiStreames, [Name2], [Name3]'
release = '0.1.0'

extensions = [
    'sphinx.ext.autodoc',
    'sphinx.ext.napoleon',
]

templates_path = ['_templates']
exclude_patterns = []

html_theme = 'alabaster'
html_static_path = ['_static']
